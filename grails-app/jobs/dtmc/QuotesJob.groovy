package dtmc

class QuotesJob {

    def dailyService

    static triggers = {
        simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {

       dailyService.updateDailyDatabase()

    }
}
