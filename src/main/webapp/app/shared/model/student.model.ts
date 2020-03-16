import { Moment } from 'moment';
import { IParent } from 'app/shared/model/parent.model';
import { ITeacher } from 'app/shared/model/teacher.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IStudent {
  id?: number;
  firstName?: string;
  lastName?: string;
  grade?: number;
  gender?: Gender;
  birthDate?: Moment;
  emr1FirstName?: string;
  emr1LastName?: string;
  emr1RelationShip?: string;
  emr1Email?: string;
  emr1PhoneNo?: number;
  emr2FirstName?: string;
  emr2LastName?: string;
  emr2RelationShip?: string;
  emr2Email?: string;
  emr2PhoneNo?: number;
  parent?: IParent;
  teacher?: ITeacher;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public grade?: number,
    public gender?: Gender,
    public birthDate?: Moment,
    public emr1FirstName?: string,
    public emr1LastName?: string,
    public emr1RelationShip?: string,
    public emr1Email?: string,
    public emr1PhoneNo?: number,
    public emr2FirstName?: string,
    public emr2LastName?: string,
    public emr2RelationShip?: string,
    public emr2Email?: string,
    public emr2PhoneNo?: number,
    public parent?: IParent,
    public teacher?: ITeacher
  ) {}
}
