import { AuthService } from './model/auth.service';
import { Module } from '@nestjs/common';
import { LoginController } from './controller/login.controller';
import { EmployeeModule } from 'src/employee/employee.module';
import { PassportModule } from '@nestjs/passport';
import { JwtModule } from '@nestjs/jwt';
import { jwtConstants } from './model/constants';
import { JwtStrategy } from './model/jwt.strategy';
import { LocalStrategy } from './model/local.strategy';

@Module({
    imports: [
        EmployeeModule,
        PassportModule,
        JwtModule.register({
            secret: jwtConstants.secret,
            signOptions: { expiresIn: '6000s' }
        })
    ],
    controllers: [
        LoginController
    ],
    providers: [
        AuthService, JwtStrategy, LocalStrategy
    ],
    exports: [
        AuthService
    ]
})
export class AuthModule {}
