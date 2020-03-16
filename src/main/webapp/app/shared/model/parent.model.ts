import { ParentOrGuardien } from 'app/shared/model/enumerations/parent-or-guardien.model';

export interface IParent {
  id?: number;
  firstName?: string;
  lastName?: string;
  parentOrGuardien?: ParentOrGuardien;
  email?: string;
  phoneNo?: number;
  addressLine1?: string;
  addressLine2?: string;
  zipcode?: number;
}

export class Parent implements IParent {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public parentOrGuardien?: ParentOrGuardien,
    public email?: string,
    public phoneNo?: number,
    public addressLine1?: string,
    public addressLine2?: string,
    public zipcode?: number
  ) {}
}
