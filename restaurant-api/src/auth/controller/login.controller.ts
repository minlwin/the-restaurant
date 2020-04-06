import { Controller, Request, Post, UseGuards, Get, Body } from '@nestjs/common';
import { AuthService } from '../model/auth.service';
import { LocalAuthGuard } from '../model/local-auth.guard';

@Controller('auth')
export class LoginController {

    constructor(
        private readonly auth:AuthService
    ){}

    @UseGuards(LocalAuthGuard)
    @Post("login")
    login(@Request() req:any) {
        console.log(req.user)
        return this.auth.login(req.user)
    }

}
