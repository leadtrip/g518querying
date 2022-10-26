package g518querying.holiday

import org.hibernate.FetchMode as FM

class HolidayService{

    /**
     select
         this_.id as id1_6_1_,
         this_.version as version2_6_1_,
         this_.location_info_id as location3_6_1_,
         this_.name as name4_6_1_,
         this_.supplier_id as supplier5_6_1_,
         locationin2_.id as id1_7_0_,
         locationin2_.version as version2_7_0_,
         locationin2_.continent_id as continen3_7_0_,
         locationin2_.country_id as country_4_7_0_
     from
        holiday this_
     inner join
         location_info locationin2_
         on this_.location_info_id=locationin2_.id
     where
        this_.name=?

     * @param name
     * @return
     */
    def getHoliday(String name) {
        Holiday.findByName(name, [fetch: ['locationInfo': 'join',           // this works
                                          'supplier.address': 'join']])     // this does not work
    }

    /**
     select
         this_.id as id1_6_0_,
         this_.version as version2_6_0_,
         this_.location_info_id as location3_6_0_,
         this_.name as name4_6_0_,
         this_.supplier_id as supplier5_6_0_
     from
        holiday this_
     where
        this_.name=?
     * @param name
     * @return
     */
    def getHolidayCriteriaStandard(String name) {
        Holiday.createCriteria().get {
            eq "name", name
        }
    }

    /**
     select
         this_.id as id1_6_2_,
         this_.version as version2_6_2_,
         this_.location_info_id as location3_6_2_,
         this_.name as name4_6_2_,
         this_.supplier_id as supplier5_6_2_,
         locationin2_.id as id1_7_0_,
         locationin2_.version as version2_7_0_,
         locationin2_.continent_id as continen3_7_0_,
         locationin2_.country_id as country_4_7_0_,
         supplier3_.id as id1_9_1_,
         supplier3_.version as version2_9_1_,
         supplier3_.address_id as address_3_9_1_,
         supplier3_.name as name4_9_1_,
         supplier3_.contact as contact5_9_1_
     from
        holiday this_
     inner join
         location_info locationin2_
         on this_.location_info_id=locationin2_.id
     inner join
         supplier supplier3_
         on this_.supplier_id=supplier3_.id
     where
        this_.name=?
     * @param name
     * @return
     */
    def getHolidayCriteriaEagerJoin(String name) {
        Holiday.createCriteria().get {
            eq "name", name
            join 'locationInfo'                     // only gets the locationInfo, not the continent & country
            join 'supplier'                         // only gets supplier
        }
    }

    /**
     select
         this_.id as id1_6_5_,
         this_.version as version2_6_5_,
         this_.location_info_id as location3_6_5_,
         this_.name as name4_6_5_,
         this_.supplier_id as supplier5_6_5_,
         locationin1_.id as id1_7_0_,
         locationin1_.version as version2_7_0_,
         locationin1_.continent_id as continen3_7_0_,
         locationin1_.country_id as country_4_7_0_,
         continent_2_.id as id1_1_1_,
         continent_2_.version as version2_1_1_,
         continent_2_.name as name3_1_1_,
         country_al3_.id as id1_2_2_,
         country_al3_.version as version2_2_2_,
         country_al3_.code as code3_2_2_,
         supplier_a4_.id as id1_9_3_,
         supplier_a4_.version as version2_9_3_,
         supplier_a4_.address_id as address_3_9_3_,
         supplier_a4_.name as name4_9_3_,
         supplier_a4_.contact as contact5_9_3_,
         address_al5_.id as id1_0_4_,
         address_al5_.version as version2_0_4_,
         address_al5_.address_line1 as address_3_0_4_
     from
        holiday this_
     inner join
         location_info locationin1_
         on this_.location_info_id=locationin1_.id
     inner join
         continent continent_2_
         on locationin1_.continent_id=continent_2_.id
     inner join
         country country_al3_
         on locationin1_.country_id=country_al3_.id
     inner join
         supplier supplier_a4_
         on this_.supplier_id=supplier_a4_.id
     inner join
         address address_al5_
         on supplier_a4_.address_id=address_al5_.id
     where
        this_.name=?

     * @param name
     * @return
     */
    def getHolidayCriteriaEagerSelects(String name) {           // just stating the associations fetches them
        Holiday.createCriteria().get {
            eq "name", name
            locationInfo {
                continent{}
                country{}
            }
            supplier {
                address{}
            }
        }
    }
}