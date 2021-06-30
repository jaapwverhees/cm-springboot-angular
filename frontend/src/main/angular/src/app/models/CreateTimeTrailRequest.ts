import {Team} from "./Team";

export class CreateTimeTrailRequest {
  name: string;
  stages: Date[];
  teams: Team[];

}
