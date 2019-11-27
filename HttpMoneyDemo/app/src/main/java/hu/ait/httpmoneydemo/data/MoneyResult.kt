package hu.ait.httpmoneydemo.data

data class MoneyResult(val rates: Rates?, val base: String?, val date: String?)

data class Rates(val CAD: Number?, val HKD: Number?, val ISK: Number?, val PHP: Number?, val DKK: Number?, val HUF: Number?, val CZK: Number?, val GBP: Number?, val RON: Number?, val SEK: Number?, val IDR: Number?, val INR: Number?, val BRL: Number?, val RUB: Number?, val HRK: Number?, val JPY: Number?, val THB: Number?, val CHF: Number?, val EUR: Number?, val MYR: Number?, val BGN: Number?, val TRY: Number?, val CNY: Number?, val NOK: Number?, val NZD: Number?, val ZAR: Number?, val USD: Number?, val MXN: Number?, val SGD: Number?, val AUD: Number?, val ILS: Number?, val KRW: Number?, val PLN: Number?)
