import { Category } from "./category.entity"
import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, JoinColumn } from "typeorm"
import { IdEnable } from "src/common/id.enable"

@Entity()
export class Product implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string

    @ManyToOne(type => Category, {
        nullable : false,
        eager: true
    })
    category:Category
    @Column()
    price:number
    @Column()
    size:String
}