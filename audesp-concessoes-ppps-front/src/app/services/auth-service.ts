import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import * as moment from "moment";

@Injectable()
export class AuthService {

    constructor(private http: HttpClient) {

    }

    public loginFromURL(url: string) {
        let unencodedUrl = decodeURIComponent(url);
        var regexResult = unencodedUrl.match("token_acesso=(\\S*)");
        if (regexResult != null && regexResult.length > 1) {
            this.setSession(regexResult[1]);
        } else {
            console.error("cannot obtain token from url. url=" + url);
        }
    }

    private setSession(token:any) {
        console.log(token);
        const expiresAt = moment().add(10, 'minute');
        localStorage.setItem("id_token", token);
        localStorage.setItem("expires_at", JSON.stringify(expiresAt));
    }

    public logout() {
        localStorage.removeItem("id_token");
        localStorage.removeItem("expires_at");
    }

    public isLoggedIn() {
        return localStorage.getItem("id_token") 
            && localStorage.getItem("expires_at")
                && moment().isBefore(this.getExpiration());
    }

    public isLoggedOut() {
        return !this.isLoggedIn();
    }

    private getExpiration() {
        const expiration: string | any = localStorage.getItem("expires_at");
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}