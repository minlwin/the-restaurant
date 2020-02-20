import { Entity, PrimaryGeneratedColumn, Column, Index } from 'typeorm'
import { IdEnable } from 'src/common/id.enable'

@Entity()
export class Tables implements IdEnable{
    @PrimaryGeneratedColumn()
    id:number
    @Index({unique : true})
    @Column()
    tableNumber:string
    @Column()
    seats?:number
}