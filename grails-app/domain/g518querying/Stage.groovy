package g518querying

import java.time.LocalDate

class Stage {

    String name
    LocalDate date
    String startLocation
    String endLocation
    Double distanceKm
    Long elevationMetres

    static constraints = {
    }
}
