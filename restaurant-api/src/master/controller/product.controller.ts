import { Controller, Get, Param, Query, Body, Post, UploadedFile, UseInterceptors, Res } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { FileInterceptor } from '@nestjs/platform-express'
import { Product } from '../model/product.entity';
import { ProductService } from '../model/product.service';
import { diskStorage } from 'multer';
import { IMAGE_HOME } from 'src/common/image.config';

@Controller('products')
export class ProductController extends BaseControllerMutable<Product> {

    constructor(private readonly productservice:ProductService) {
        super(productservice, '/products')
    }

    @Get('category/:id')
    findByCategory(@Param('id') categoryId:number) {
        return this.productservice.findByCategory(categoryId)
    }

    @Get('search')
    search( @Query('type') type:string, @Query('categoryId') categoryId:number = 0, @Query('name') name:string) {
        return this.productservice.search(type, categoryId, name)
    }

    @Post('upload')
    upload(@Body() list:Product[]) {
        return this.productservice.upload(list)
    }

    @Post('photo/:id')
    @UseInterceptors(FileInterceptor('photo', {
        storage: diskStorage({
            destination: IMAGE_HOME,
            filename: (req, file, cb) => {
                return cb(null, `${file.originalname}.png`)
            }
        })
    }))
    async uploadPhoto(@Param('id') id:number, @UploadedFile() file:any) {
        var menu:Product = await this.productservice.findById(id)
        menu.image = file.filename
        return this.productservice.save(menu)
    }

    @Get('photo/:image')
    getImage(@Param('image') image:string, @Res() res:any) {
        return res.sendFile(image, {root : IMAGE_HOME})
    }
}
