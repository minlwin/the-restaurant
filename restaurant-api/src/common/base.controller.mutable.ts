import { BaseServiceMutable } from "./base.service.mutable";
import { Get, Param, Post, Body, Put, UseInterceptors, ClassSerializerInterceptor, Res } from "@nestjs/common";
import { IdEnable } from "./id.enable";
import { BaseController } from "./base.controller";


export class BaseControllerMutable<T extends IdEnable> extends BaseController<T> {

    constructor(private readonly mutableService:BaseServiceMutable<T>, 
        private readonly path:string) {
            super(mutableService)
    }

    @Post()
    async create(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }

    @Put()
    async update(@Body() t:T, @Res() res:any) {
        let savedResult = await this.mutableService.save(t)
        res.redirect(`${this.path}/${savedResult.id}`)
    }
}