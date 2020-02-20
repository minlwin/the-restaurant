import { PrimaryGeneratedColumn, Entity, ManyToOne, Column, CreateDateColumn } from "typeorm";
import { Sale } from "./sale.entity";
import { Exclude } from "class-transformer";
import { IdEnable } from "src/common/id.enable";
import { Product } from "src/master/model/product.entity";

@Entity()
export class SaleDetails implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @ManyToOne(type => Sale, sale => sale.details, {
        nullable : false
    })
    @Exclude()
    sale:Sale
    @ManyToOne(type => Product, {
        eager: true,
        nullable: false
    })
    product:Product
    @Column()
    unitPrice:number
    @Column()
    quantity:number
    @Column()
    status?:string
    @Column()
    remind?:number
    @CreateDateColumn()
    orderTime?:Date
}