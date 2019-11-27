package hu.ait.android.httpmoneydemo.data

data class MoneyResult(val date: String?, val rates: Rates?, val base: String?)

data class Rates(val BGN: Number?, val CAD: Number?, val BRL: Number?, val HUF: Number?, val DKK: Number?, val JPY: Number?, val ILS: Number?, val TRY: Number?, val RON: Number?, val GBP: Number?, val PHP: Number?, val HRK: Number?, val NOK: Number?, val USD: Number?, val MXN: Number?, val AUD: Number?, val IDR: Number?, val KRW: Number?, val HKD: Number?, val ZAR: Number?, val ISK: Number?, val CZK: Number?, val THB: Number?, val MYR: Number?, val NZD: Number?, val PLN: Number?, val SEK: Number?, val RUB: Number?, val CNY: Number?, val SGD: Number?, val CHF: Number?, val INR: Number?)
