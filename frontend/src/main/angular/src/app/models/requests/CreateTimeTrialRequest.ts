import {Team} from "../competition/Team";

export class CreateTimeTrialRequest {
  name: string;
  stages: Date[];
  teams: Team[];

}
