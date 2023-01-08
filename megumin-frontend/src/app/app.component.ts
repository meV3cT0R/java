import { Component,OnInit, Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { megumin } from "./megumin";
import { TotalCount } from './TotalCount';
import { catchError, throwError } from 'rxjs';

const httpOptions = {
  headers : new HttpHeaders({
    "Content-Type" : "application/json"
  })
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'megumin-frontend';
  id :number = 1;
  megumins :any;
  meguminNew : megumin = {
    id : -1,
    imageURL : ""
  };
  updateId = null;
  randomNumber = 1;
  totalCount :TotalCount = {
    totalCount :1
  };
  randomMegumin: megumin = {
    id : -1,
    imageURL : ""
  }
  constructor(private httpClient: HttpClient){}
  ngOnInit() {
    this.loadMegumin();
    
  }

  loadMegumin() : void {
    this.httpClient.get<megumin>("http://localhost:8080/api/megumins/"+this.id).pipe(catchError(error=> {
      this.id=1;
      this.loadMegumin();
      console.log(`${error.status} : ${error.error}`);
      return throwError(()=> new Error("Something went wrong fuck off"));
    })).subscribe(data => {
      console.log(data);
      if(data.imageURL.trim().match(" ") || data.imageURL.trim() == "") {
        this.id =1;
        this.loadMegumin();
        return;
      }
      this.megumins = data
      this.randomMeguminGenerator();
    } );
  }

  nextMegumin() : void {
    this.id++;
    this.loadMegumin();
  }

  prevMegumin() : void {
    this.id--;
    this.loadMegumin();
  }

  postMegumin(): void {
    console.log(this.meguminNew);
    if(this.meguminNew.id == -1) {
      this.httpClient.post<megumin>("http://localhost:8080/api/megumins/add",{
        "imageURL" : this.meguminNew.imageURL
      },httpOptions)
      .pipe(catchError(error=>this.handleError(error))).subscribe(data=>{
        console.log(data);
      });
    }else {
      this.updateMegumin();
    }
    this.meguminNew = {
      id : -1,
      imageURL : ""
    }
  }

  updateMegumin() : void {
    this.httpClient.put<megumin>("http://localhost:8080/api/megumins/update",this.meguminNew,httpOptions).pipe(catchError(this.handleError))
    .subscribe(data=>{console.log(data)});
  }

  randomMeguminGenerator() : void {
    this.httpClient.get<TotalCount>("http://localhost:8080/api/megumins/totalmegumin").subscribe(data=>this.totalCount=data);
    this.randomNumber = (Math.random() * this.totalCount.totalCount)+1;
    this.httpClient.get<megumin>(`http://localhost:8080/api/megumins/${parseInt(this.randomNumber.toString())}`)
    .subscribe(data=>this.randomMegumin=data);
    console.log(this.randomNumber);
    console.log(`Total Count : ${this.totalCount.totalCount}`);
  }
  private handleError(error: HttpErrorResponse) {
    if(error.status === 0) {
      console.log("Everything's fine at the server side");
    }else {
      console.error(`Backed returned code ${error.status}, body was ${error.error}`);
    }
    return throwError(()=>new Error("Something Went Wrong, please die"));
  }
}
