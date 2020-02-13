import { IdEnable } from "src/common/id.enable"
import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from "typeorm"
import { Category } from "./category.entity"

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