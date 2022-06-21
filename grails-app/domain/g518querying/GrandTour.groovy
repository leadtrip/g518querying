package g518querying


import java.time.LocalDate
import java.time.Year

class GrandTour {

    String name
    Year year
    Country country
    LocalDate start
    LocalDate end
    static hasMany = [stages: Stage]

    static constraints = {
    }
}
