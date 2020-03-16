export interface ITeacher {
  id?: number;
  firstName?: string;
  lastName?: string;
}

export class Teacher implements ITeacher {
  constructor(public id?: number, public firstName?: string, public lastName?: string) {}
}
