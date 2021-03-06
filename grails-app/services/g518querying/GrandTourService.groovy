package g518querying

import grails.gorm.transactions.Transactional

import java.time.Year

@Transactional
class GrandTourService {

    def allTours() {
        GrandTour.all
    }

    def allToursForCountry( country ) {
        GrandTour.where { country == country }.list()
    }

    def allToursExcluding( excludeList ) {
        GrandTour.where { !(id in excludeList) }.list()
    }

    def allToursExcludingAfter( excludeList, startDate ) {
        GrandTour.where { !(id in excludeList) && start > startDate }.list()
    }

    def allToursForDates( startDate, endDate ) {
        GrandTour.where { start in  startDate..endDate }.list()
    }

    def findByCountry( Country country ) {
        GrandTour.findAll( "from GrandTour g where g.country=:country", [country: country] )
    }

    def findByYearIn( Integer... years ) {
        GrandTour.findAll( "from GrandTour g where g.year in (:years)", [years: years.collect { Year.of(it)}] )
    }
}
