import { Injectable } from "@nestjs/common";
import { Strategy, ExtractJwt } from "passport-jwt"
import { PassportStrategy } from "@nestjs/passport"
import { jwtConstants } from "./constants";


@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy){

    constructor() {
        super({
            jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
            ignoreExpiration: true,
            secretOrKey: jwtConstants.secret
        })
    }

    authenticate(req: any, options?: any) {
        super.authenticate(req, options)
    }

    async validate(payload:any) {
        return { email: payload.username, role: payload.sub }
    }

}