import { Injectable, UnauthorizedException } from "@nestjs/common";
import { Strategy } from "passport-local";
import { PassportStrategy } from "@nestjs/passport";
import { AuthService } from "./auth.service";

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy) {

    constructor(
        private readonly service:AuthService
    ) {
        super()
    }

    async validate(username:string, password:string):Promise<any> {
        const user = this.service.validateUser(username, password)

        if(!user) {
            throw new UnauthorizedException()
        }

        return user
    }
}