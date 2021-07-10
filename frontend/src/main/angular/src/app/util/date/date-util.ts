export class DateUtil {
  static currentDateBeforeStage(date : Date) {
    let d = new Date();
    var g1 = new Date(d.getFullYear(), d.getMonth(), d.getDate());
    return g1.getTime() > date.getTime();
  }
}
