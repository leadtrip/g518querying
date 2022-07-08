package g518querying

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

import java.time.LocalDate
import java.time.Month
import java.time.Year

/**
 * We need to use HibernateSpec to test some types of grails query such as findBy
 */
class GrandTourSerivceHibernateSpec extends HibernateSpec implements ServiceUnitTest<GrandTourService> {

    List<Class> getDomainClasses() { [GrandTour] }

    def setup() {
        new GrandTour( name: 'Giro 2021', year: Year.parse('2021'), country: Country.ITALY, start: LocalDate.of(2021, Month.MAY, 8), end: LocalDate.of(2021, Month.MAY, 30) ).save()

        def tdf2022 = new GrandTour( name: 'TDF 2022', year: Year.parse('2022'), country: Country.FRANCE, start: LocalDate.of(2022, Month.JULY, 1), end: LocalDate.of(2022, Month.JULY, 24) )
        tdf2022.addToStages( new Stage(name: 'ITT', date: LocalDate.of(2022, Month.JULY, 1), startLocation: 'Copehnagen', endLocation: 'Copenhagen', distanceKm: 13.2, elevationMetres: 43) )
        tdf2022.addToStages( new Stage(name: 'ROSKILDE > NYBORG', date: LocalDate.of(2022, Month.JULY, 2), startLocation: 'ROSKILDE', endLocation: 'NYBORG', distanceKm: 202.5, elevationMetres: 1174) )
        tdf2022.addToStages( new Stage(name: 'VEJLE > SØNDERBORG', date: LocalDate.of(2022, Month.JULY, 3), startLocation: 'VEJLE', endLocation: 'SØNDERBORG', distanceKm: 182, elevationMetres: 1409) )
        tdf2022.addToStages( new Stage(name: 'DUNKERQUE > CALAIS', date: LocalDate.of(2022, Month.JULY, 5), startLocation: 'DUNKERQUE', endLocation: 'CALAIS', distanceKm: 171.5, elevationMetres: 1894) )
        tdf2022.addToStages( new Stage(name: 'LILLE MÉTROPOLE > ARENBERG PORTE DU HAINAUT', date: LocalDate.of(2022, Month.JULY, 6), startLocation: 'LILLE MÉTROPOLE', endLocation: 'ARENBERG PORTE DU HAINAUT', distanceKm: 154, elevationMetres: 530) )
        tdf2022.save()

        def laVuelta2022 = new GrandTour( name: 'La Vuelta 2022', year: Year.parse('2022'), country: Country.SPAIN, start: LocalDate.of(2022, Month.AUGUST, 19), end: LocalDate.of(2022, Month.SEPTEMBER, 11) )
        laVuelta2022.addToStages( new Stage(name: 'ITT', date: LocalDate.of(2022, Month.AUGUST, 19), startLocation: 'Utrecht ', endLocation: 'Utrecht ', distanceKm: 23.2, elevationMetres: 43) )
        laVuelta2022.addToStages( new Stage(name: 's-Hertogenbosch > Utrecht', date: LocalDate.of(2022, Month.AUGUST, 20), startLocation: 's-Hertogenbosch', endLocation: 'Utrecht', distanceKm: 175.1, elevationMetres: 43) )
        laVuelta2022.addToStages( new Stage(name: 'Breda > Breda', date: LocalDate.of(2022, Month.AUGUST, 21), startLocation: 'Breda', endLocation: 'Breda', distanceKm: 193.2, elevationMetres: 43) )
        laVuelta2022.addToStages( new Stage(name: 'Vitoria-Gasteiz > Laguardia', date: LocalDate.of(2022, Month.AUGUST, 23), startLocation: 'Vitoria-Gasteiz', endLocation: 'Laguardia', distanceKm: 153.5, elevationMetres: 43) )
        laVuelta2022.addToStages( new Stage(name: 'Irun > Bilbao', date: LocalDate.of(2022, Month.AUGUST, 24), startLocation: 'Irun', endLocation: 'Bilbao', distanceKm: 187, elevationMetres: 43) )
        laVuelta2022.save()
    }

    void "test findByCountry"() {
        when:
            def res = service.findByCountry(Country.FRANCE)
        then:
            res.size() == 1
            res[0].name == 'TDF 2022'
    }

    void "test findByYearIn"() {
        when:
            def res = service.findByYearIn(2021, 2022)
        then:
            res.size() == 3
        when:
            res = service.findByYearIn( 2021 )
        then:
            res.size() == 1
    }
}
