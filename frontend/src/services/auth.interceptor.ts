import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";//used to implement interface
import { AuthorizationService } from "./authorization.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private loginService:AuthorizationService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        //throw new Error("Method not implemented.");
        let newReq=req;
        let token= this.loginService.getToken();
        console.log("INTERCEPTOR",token);
        
        if(token!=null){
           newReq= newReq.clone({setHeaders:{Authorization:`Bearer ${token}`}});
        }
        
        return next.handle(newReq);
    }
    
}