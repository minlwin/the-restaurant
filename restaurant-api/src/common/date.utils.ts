import moment = require("moment")

export const currentDate = function() {
    return yangonTime(new Date())
}

export const currentDateStart = function() {
    return yangonTime(moment().startOf('date').toDate())
}

export const currentDateEnd = function() {
    return yangonTime(moment().endOf('date').toDate())
}

function yangonTime(rawDate:Date) {
    rawDate.setHours(rawDate.getHours() + 6)
    rawDate.setMinutes(rawDate.getMinutes() + 30)
    return rawDate
}