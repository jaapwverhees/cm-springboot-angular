import {Score} from "./Score";
import {Team} from "./Team";

export class Game {
  id: number;
  teamOne: Score;
  teamTwo: Score;
  gameIndex: number;

  getTeams():Team[] {
    return [this.teamOne.team, this.teamTwo.team];
  }
}
