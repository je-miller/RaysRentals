export interface IVisitorLocation {
  id?: string;
  ip?: string;
  countryCode?: string;
  countryName?: string;
  region?: string;
  city?: string;
  latitude?: number;
  longitude?: number;
  zipcode?: string;
  timezone?: string;
}

export class VisitorLocation implements IVisitorLocation {
  constructor(
    public id?: string,
    public ip?: string,
    public countryCode?: string,
    public countryName?: string,
    public region?: string,
    public city?: string,
    public latitude?: number,
    public longitude?: number,
    public zipcode?: string,
    public timezone?: string
  ) {}
}
