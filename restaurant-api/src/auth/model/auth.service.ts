import { Injectable } from '@nestjs/common';
import { UserService } from 'src/employee/model/user.service';
import { JwtService } from '@nestjs/jwt'
import bcrypt = require('bcrypt')

@Injectable()
export class AuthService {

    constructor(
        private readonly userService:UserService,
        private readonly jwtService:JwtService
    ) {}

    async validateUser(userName:string, pass:string):Promise<any> {
        let user = await this.userService.findByLoginId(userName)

        if(user && bcrypt.compareSync(pass, user.password)) {
            const { password , ... results} = user
            return results
        }

        return null
    }

    async login(user:any) {
        let payload = { username : user.email, sub : user.role }
        return {
            user: user,
            token : this.jwtService.sign(payload)
        }
    }
}
