import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from 'src/services/authorization.service';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  credentials={
    userid:"" ,
    upassword:"",
    uname:""  
  }
  
  constructor(private loginService:AuthorizationService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log("form submitted");
    if((this.credentials.userid!='' && this.credentials.uname!='' && this.credentials.upassword!='') && 
    (this.credentials.userid!=null && this.credentials.uname!=null && this.credentials.upassword!=null)){
      this.loginService.generateToken(this.credentials).subscribe(
        (response: any)=>{
          console.log("success");
          console.log(response);
          console.log(response.authToken);
          console.log(response.userid);
          this.loginService.loginUser(response.authToken,response.userid);
          window.location.href="/dashboard";
        },
        error =>{
          console.log("error");
          console.log(error);
          
        }
      );
    }
  }
}
