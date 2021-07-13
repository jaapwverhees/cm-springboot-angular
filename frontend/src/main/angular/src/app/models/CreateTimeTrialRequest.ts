import {Team} from "./Team";

export class CreateTimeTrialRequest {
  name: string;
  stages: Date[];
  teams: Team[];

}
