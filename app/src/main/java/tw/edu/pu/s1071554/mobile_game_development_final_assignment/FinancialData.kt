package tw.edu.pu.s1071554.mobile_game_development_final_assignment

// 財務資料
class FinancialData {

    var id: Int = -1            // 資料 ID
    var time: String = "//"     // 時間
    var amount: String = "0"         // 金額
    var message: String = ""    // 描述訊息

    constructor(_i: Int, _t: String, _a: String, _m: String) {
        id = _i
        time = _t
        amount = _a
        message = _m
    }

}