import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParent } from 'app/shared/model/parent.model';

type EntityResponseType = HttpResponse<IParent>;
type EntityArrayResponseType = HttpResponse<IParent[]>;

@Injectable({ providedIn: 'root' })
export class ParentService {
  public resourceUrl = SERVER_API_URL + 'api/parents';

  constructor(protected http: HttpClient) {}

  create(parent: IParent): Observable<EntityResponseType> {
    return this.http.post<IParent>(this.resourceUrl, parent, { observe: 'response' });
  }

  update(parent: IParent): Observable<EntityResponseType> {
    return this.http.put<IParent>(this.resourceUrl, parent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
