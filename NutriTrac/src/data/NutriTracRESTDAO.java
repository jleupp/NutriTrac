package data;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import entities.Food;
import entities.Measure;
import entities.Nutrient;


@Transactional

public class NutriTracRESTDAO {

	@PersistenceContext

	private EntityManager em;
	
	
	
	
	
	
	
	
	// ------------ FOOD
	
	public Food getFoodById(String ndbnoParameter) {

		int ndbno = Integer.parseInt(ndbnoParameter.trim());

		Food food = em.find(Food.class, ndbno);

		System.out.println(food);

		return food;

	}
	
	public ArrayList<Food> getAllFoodsByName (String nameParameter) {

		String name = nameParameter.trim();

		ArrayList<Food> getAllFoodsByName = (ArrayList<Food>) em.createNamedQuery("Food.getAllFoodsByName").setParameter("name", name).getResultList();

		return getAllFoodsByName;

	}
	
	
	

	public  ArrayList<Food> getAllFoods() {

		ArrayList<Food> allFoods = (ArrayList<Food>) em.createNamedQuery("Food.getAllFoods").getResultList();

		return allFoods;

	}
	
	
	
	public Food createFood(Food f) {
		
		Food fu = new Food();
		
		Measure myMeasure = new Measure();
		Nutrient myNutrient = new Nutrient();
		ArrayList<Measure> myListOfMeasures = new ArrayList<>();
		ArrayList<Nutrient> myListOfNutrients = new ArrayList<>();
		
		myMeasure.setEqv(12.5);
		myMeasure.setFood(fu);
		myMeasure.setLabel("myLabel");
		myMeasure.setNutrient(myNutrient);
		myMeasure.setQty(5.5);
		myMeasure.setValue("0.045");
		
		myNutrient.setFood(fu);
		myNutrient.setName("Protein");
		myNutrient.setGroup("Proximates");
		myNutrient.setUnit("cup");
		myNutrient.setValue("0.234");
		myNutrient.setMeasures(myListOfMeasures);
		
		myListOfMeasures.add(myMeasure);
		myListOfNutrients.add(myNutrient);
		
		fu.setName("Oatmeal");
		fu.setNdbno(1234);
		fu.setMeasures(myListOfMeasures);
		fu.setNutrients(myListOfNutrients);
		
		
		
		
		
		//Food f = em.find(Food.class, f.getNdbno())
		String ndbno = f.getNdbno() + " ";
		Food myfood = getFoodById(ndbno);
 
		if (myfood == null) {
		
		em.merge(f);

		em.persist(f);

		Food persistedFood = (Food) em.createNamedQuery("Food.getLastFoodById").getSingleResult();

		return persistedFood;

		
		}
		
		else return null;
	}
	
	public Food updateFood(Food f) {

		em.merge(f);

		//em.persist(e);

		int ndbno = f.getNdbno();

		Food persistedFood = em.find(Food.class, ndbno);

		return persistedFood;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	//  ---------------------------------  NUTRIENT
	
	
	
	public Nutrient getNutrientById(String idParameter) {

		int id = Integer.parseInt(idParameter.trim());

		Nutrient nutrient = em.find(Nutrient.class, id);

		System.out.println(nutrient);

		return nutrient;

	}
	
	
   public  ArrayList<Nutrient> getAllNutrients() {

	   ArrayList<Nutrient> allNutrients = (ArrayList<Nutrient>) em.createNamedQuery("Nutrient.getAllNutrients").getResultList();

	return allNutrients;

}
   
   
   //------------------------------------ MEASURE
   
	public Measure getMeasureById(String measureIdParameter) {

		int measureId = Integer.parseInt(measureIdParameter.trim());

		Measure measure = em.find(Measure.class, measureId);

		System.out.println(measure);

		return measure;

	}
   
   public  ArrayList<Measure> getAllMeasures() {

	   ArrayList<Measure> allMeasures = (ArrayList<Measure>) em.createNamedQuery("Measure.getAllMeasures").getResultList();

	return allMeasures;

}	
}