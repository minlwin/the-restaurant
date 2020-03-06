import { Body, Post, Put, Res, UseGuards } from "@nestjs/common";
import { JwtAuthGuard } from "src/auth/model/jwt-auth.guard";
import { BaseController } from "./base.controller";
import { BaseServiceMutable } from "./base.service.mutable";
import { IdEnable } from "./id.enable";


export class BaseControllerMutable<T extends IdEnable> extends BaseController<T> {

    constructor(private readonly mutableService:BaseServiceMutable<T>, 
        private readonly path:string) {
            super(mutableService)
    }

    @Post()
    @UseGuards(JwtAuthGuard)
    async create(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }

    @Put()
    @UseGuards(JwtAuthGuard)
    async update(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }
}