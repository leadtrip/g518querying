package g518querying.holiday

import grails.gorm.transactions.Rollback
import grails.gorm.transactions.Transactional
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
class HolidayIntegrationSpec extends Specification{

    HolidayService holidayService

    def "test findBy"() {
        given:
            addHoliday()
        when:
            def myHol = holidayService.getHoliday('MyHol')
        then:
            validateHoliday(myHol)
    }

    def "test criteria standard"() {
        given:
            addHoliday()
        when:
            Holiday myHol = holidayService.getHolidayCriteriaStandard('MyHol')
        then:
            validateHoliday(myHol)
    }

    def "test criteria eager join"() {
        given:
            addHoliday()
        when:
            Holiday myHol = holidayService.getHolidayCriteriaEagerJoin('MyHol')
        then:
            validateHoliday(myHol)
    }

    def "test criteria eager selects"() {
        given:
            addHoliday()
        when:
           Holiday myHol = holidayService.getHolidayCriteriaEagerSelects('MyHol')
        then:
            validateHoliday(myHol)
    }

    def validateHoliday(Holiday myHol) {
        assert myHol.name == 'MyHol'
        assert myHol.locationInfo.country.code == 'UK'
        assert myHol.locationInfo.continent.name == 'Europe'
        assert myHol.supplier.name == 'A1'
        assert myHol.supplier.address.addressLine1 == '102 some place'
        true
    }

    @Transactional
    def addHoliday() {
        def supplierAddress = new Address(addressLine1: '102 some place')
        def supplier = new Supplier(name: 'A1', contact: 'Dave', address: supplierAddress)
        def country = new Country(code: 'UK')
        def continent = new Continent(name: 'Europe')
        def locationInfo = new LocationInfo(continent: continent, country: country)
        new Holiday(name: 'MyHol', locationInfo: locationInfo, supplier: supplier).save()
    }
}
