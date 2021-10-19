import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth-service';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService,
        private router: Router) {
        
    }
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token: any;
        console.log("current auth-token=" + token);
        if (!this.authService.isLoggedIn()) {
            this.authService.loginFromURL(window.location.href);
        }
        token = localStorage.getItem('id_token');
        const cloned = req.clone({
            headers: req.headers.set('Authorization', 'Bearer ' + token)
        });
        return next.handle(cloned).pipe(
            tap(
                event => {
                    console.log("API authentication succeeded");
                },
                error => {
                    console.log("API request failed, auth-token=" + token);
                    if (error.status === 401) {
                        this.authService.logout();
					    window.location.href = environment.DelegacoesUrl
                    }
                }
            )
        )
    }
}