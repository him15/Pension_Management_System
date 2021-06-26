import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  authenticate_url="http://localhost:9095"

  constructor(private http:HttpClient) {  }

  generateToken(credentials: any){
    return this.http.post(`${this.authenticate_url}/login`,credentials);
  }
  //login user
  loginUser(token: string,userId : string){
    localStorage.setItem("token",token);
    localStorage.setItem("userId",userId);
    return true;
  }
  isLoggedIn(){
    let token=localStorage.getItem("token");
    if(token==undefined||token==''||token==null){
      return false;
    }
    else{
      return true;
    }
  }
  getToken(){
    return localStorage.getItem("token");
  }
  logOut(){
    localStorage.removeItem("token");
  }
}
