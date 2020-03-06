import { Controller, Request, Post, UseGuards, Get } from '@nestjs/common';
import { AuthService } from '../model/auth.service';
import { LocalAuthGuard } from '../model/local-auth.guard';
import { JwtAuthGuard } from '../model/jwt-auth.guard';

@Controller('auth')
export class LoginController {

    constructor(
        private readonly auth:AuthService
    ){}

    @UseGuards(LocalAuthGuard)
    @Post("login")
    login(@Request() req:any) {
        return this.auth.login(req.user)
    }

    @UseGuards(JwtAuthGuard)
    @Get()
    test() {
        return "Login Success"
    }
}
