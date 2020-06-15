package diplomski.nutrition.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;//created by user
	
	@OneToMany(mappedBy = "food", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<FoodMeal> meals = new HashSet<FoodMeal>();
	
	@OneToMany(mappedBy = "food", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RecipeFood> recipes = new HashSet<RecipeFood>();
	
	private String name;
	
	private Integer totalCalories;//kcal in 100g
	
	private String servingSize;//100g
//	private Float servingSizeQuantity;
	
	private Float totalFat;
	private Float saturatedFat;
	private Float cholesterol;
	private Float sodium;
	private Float totalCarbs;
	private Float fiber;
	private Float sugars;
	private Float protein;
	private Float vitaminA;
	private Float vitaminC;
	private Float vitaminD;
	private Float calcium;
	private Float iron;
	private Float phosphorus;
	private Float potasium;
	
	public Food() {
		super();
	}

	public Food(Long id, User user, Set<FoodMeal> meals, Set<RecipeFood> recipes, String name, Integer totalCalories,
			Float totalFat, Float saturatedFat, Float cholesterol, Float sodium, Float totalCarbs, Float fiber,
			Float sugars, Float protein, Float vitaminA, Float vitaminC, Float vitaminD, Float calcium, Float iron,
			Float phosphorus, Float potasium) {
		super();
		this.id = id;
		this.user = user;
		this.meals = meals;
		this.recipes = recipes;
		this.name = name;
		this.totalCalories = totalCalories;
		this.totalFat = totalFat;
		this.saturatedFat = saturatedFat;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.totalCarbs = totalCarbs;
		this.fiber = fiber;
		this.sugars = sugars;
		this.protein = protein;
		this.vitaminA = vitaminA;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.calcium = calcium;
		this.iron = iron;
		this.phosphorus = phosphorus;
		this.potasium = potasium;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Integer totalCalories) {
		this.totalCalories = totalCalories;
	}

	public Set<FoodMeal> getMeals() {
		return meals;
	}

	public void setMeals(Set<FoodMeal> meals) {
		this.meals = meals;
	}

	public Set<RecipeFood> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeFood> recipes) {
		this.recipes = recipes;
	}

	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

//	public Float getServingSizeQuantity() {
//		return servingSizeQuantity;
//	}
//
//	public void setServingSizeQuantity(Float servingSizeQuantity) {
//		this.servingSizeQuantity = servingSizeQuantity;
//	}

	public Float getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Float totalFat) {
		this.totalFat = totalFat;
	}

	public Float getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(Float saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public Float getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Float cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Float getSodium() {
		return sodium;
	}

	public void setSodium(Float sodium) {
		this.sodium = sodium;
	}

	public Float getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(Float totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public Float getFiber() {
		return fiber;
	}

	public void setFiber(Float fiber) {
		this.fiber = fiber;
	}

	public Float getSugars() {
		return sugars;
	}

	public void setSugars(Float sugars) {
		this.sugars = sugars;
	}

	public Float getProtein() {
		return protein;
	}

	public void setProtein(Float protein) {
		this.protein = protein;
	}

	public Float getVitaminA() {
		return vitaminA;
	}

	public void setVitaminA(Float vitaminA) {
		this.vitaminA = vitaminA;
	}

	public Float getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Float vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Float getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Float vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Float getCalcium() {
		return calcium;
	}

	public void setCalcium(Float calcium) {
		this.calcium = calcium;
	}

	public Float getIron() {
		return iron;
	}

	public void setIron(Float iron) {
		this.iron = iron;
	}

	public Float getPhosphorus() {
		return phosphorus;
	}

	public void setPhosphorus(Float phosphorus) {
		this.phosphorus = phosphorus;
	}

	public Float getPotasium() {
		return potasium;
	}

	public void setPotasium(Float potasium) {
		this.potasium = potasium;
	}
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String food_name;
//	private String brand_name;
//	private Integer serving_qty;
//	private String serving_unit;
//	private Float serving_weight_grams;
//	private Float nf_calories;
//	private Float nf_total_fat;
//	private Float nf_saturated_fat;
//	private Float nf_cholesterol;
//	private Float nf_sodium;
//	private Float nf_total_carbohydrate;
//	private Float nf_dietary_fiber;
//	private Float nf_sugars;
//	private Float nf_protein;
//	private Float nf_potassium;
//	private Float nf_p;
//	private Set<Nutrient> full_nutrients;
//	private String nix_brand_name;
//	private String nix_brand_id;
//	private String nix_item_name;
//	private String nix_item_id;
//	private String upc;
	
	/*
	 * "nix_brand_name": null,
	            "nix_brand_id": null,
	            "nix_item_name": null,
	            "nix_item_id": null,
	            "upc": null,
	            "consumed_at": "2020-04-27T21:50:18+00:00",
	            "metadata": {
	                "is_raw_food": false
	            },
	            "source": 1,
	            "ndb_no": 9003,
	            "tags": {
	                "item": "apple",
	                "measure": null,
	                "quantity": "1.0",
	                "food_group": 3,
	                "tag_id": 384
	            },
	            "alt_measures": [
	                {
	                    "serving_weight": 242,
	                    "measure": "NLEA serving",
	                    "seq": 7,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 101,
	                    "measure": "extra small (2-1/2\" dia)",
	                    "seq": 6,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 149,
	                    "measure": "small (2-3/4\" dia)",
	                    "seq": 5,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 182,
	                    "measure": "medium (3\" dia)",
	                    "seq": 4,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 223,
	                    "measure": "large (3-1/4\" dia)",
	                    "seq": 3,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 109,
	                    "measure": "cup slices",
	                    "seq": 2,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 125,
	                    "measure": "cup, quartered or chopped",
	                    "seq": 1,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 28.35,
	                    "measure": "oz",
	                    "seq": 80,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 100,
	                    "measure": "g",
	                    "seq": null,
	                    "qty": 100
	                }
	            ],
	            "lat": null,
	            "lng": null,
	            "meal_type": 5,
	            "photo": {
	                "thumb": "https://d2xdmhkmkbyw75.cloudfront.net/384_thumb.jpg",
	                "highres": "https://d2xdmhkmkbyw75.cloudfront.net/384_highres.jpg",
	                "is_user_uploaded": false
	            },
	            "sub_recipe": null
	{
	    "foods": [
	        {
	            "food_name": "apple",
	            "brand_name": null,
	            "serving_qty": 1,
	            "serving_unit": "medium (3\" dia)",
	            "serving_weight_grams": 182,
	            "nf_calories": 94.64,
	            "nf_total_fat": 0.31,
	            "nf_saturated_fat": 0.05,
	            "nf_cholesterol": 0,
	            "nf_sodium": 1.82,
	            "nf_total_carbohydrate": 25.13,
	            "nf_dietary_fiber": 4.37,
	            "nf_sugars": 18.91,
	            "nf_protein": 0.47,
	            "nf_potassium": 194.74,
	            "nf_p": 20.02,
	            "full_nutrients": [
	                {
	                    "attr_id": 203,
	                    "value": 0.4732
	                },
	                {
	                    "attr_id": 204,
	                    "value": 0.3094
	                },
	                {
	                    "attr_id": 205,
	                    "value": 25.1342
	                },
	                {
	                    "attr_id": 207,
	                    "value": 0.3458
	                },
	                {
	                    "attr_id": 208,
	                    "value": 94.64
	                },
	                {
	                    "attr_id": 209,
	                    "value": 0.091
	                },
	                {
	                    "attr_id": 210,
	                    "value": 3.7674
	                },
	                {
	                    "attr_id": 211,
	                    "value": 4.4226
	                },
	                {
	                    "attr_id": 212,
	                    "value": 10.738
	                },
	                {
	                    "attr_id": 213,
	                    "value": 0
	                },
	                {
	                    "attr_id": 214,
	                    "value": 0
	                },
	                {
	                    "attr_id": 221,
	                    "value": 0
	                },
	                {
	                    "attr_id": 255,
	                    "value": 155.7192
	                },
	                {
	                    "attr_id": 262,
	                    "value": 0
	                },
	                {
	                    "attr_id": 263,
	                    "value": 0
	                },
	                {
	                    "attr_id": 268,
	                    "value": 396.76
	                },
	                {
	                    "attr_id": 269,
	                    "value": 18.9098
	                },
	                {
	                    "attr_id": 287,
	                    "value": 0
	                },
	                {
	                    "attr_id": 291,
	                    "value": 4.368
	                },
	                {
	                    "attr_id": 301,
	                    "value": 10.92
	                },
	                {
	                    "attr_id": 303,
	                    "value": 0.2184
	                },
	                {
	                    "attr_id": 304,
	                    "value": 9.1
	                },
	                {
	                    "attr_id": 305,
	                    "value": 20.02
	                },
	                {
	                    "attr_id": 306,
	                    "value": 194.74
	                },
	                {
	                    "attr_id": 307,
	                    "value": 1.82
	                },
	                {
	                    "attr_id": 309,
	                    "value": 0.0728
	                },
	                {
	                    "attr_id": 312,
	                    "value": 0.0491
	                },
	                {
	                    "attr_id": 313,
	                    "value": 6.006
	                },
	                {
	                    "attr_id": 315,
	                    "value": 0.0637
	                },
	                {
	                    "attr_id": 317,
	                    "value": 0
	                },
	                {
	                    "attr_id": 318,
	                    "value": 98.28
	                },
	                {
	                    "attr_id": 319,
	                    "value": 0
	                },
	                {
	                    "attr_id": 320,
	                    "value": 5.46
	                },
	                {
	                    "attr_id": 321,
	                    "value": 49.14
	                },
	                {
	                    "attr_id": 322,
	                    "value": 0
	                },
	                {
	                    "attr_id": 323,
	                    "value": 0.3276
	                },
	                {
	                    "attr_id": 324,
	                    "value": 0
	                },
	                {
	                    "attr_id": 328,
	                    "value": 0
	                },
	                {
	                    "attr_id": 334,
	                    "value": 20.02
	                },
	                {
	                    "attr_id": 337,
	                    "value": 0
	                },
	                {
	                    "attr_id": 338,
	                    "value": 52.78
	                },
	                {
	                    "attr_id": 341,
	                    "value": 0
	                },
	                {
	                    "attr_id": 342,
	                    "value": 0
	                },
	                {
	                    "attr_id": 343,
	                    "value": 0
	                },
	                {
	                    "attr_id": 401,
	                    "value": 8.372
	                },
	                {
	                    "attr_id": 404,
	                    "value": 0.0309
	                },
	                {
	                    "attr_id": 405,
	                    "value": 0.0473
	                },
	                {
	                    "attr_id": 406,
	                    "value": 0.1656
	                },
	                {
	                    "attr_id": 410,
	                    "value": 0.111
	                },
	                {
	                    "attr_id": 415,
	                    "value": 0.0746
	                },
	                {
	                    "attr_id": 417,
	                    "value": 5.46
	                },
	                {
	                    "attr_id": 418,
	                    "value": 0
	                },
	                {
	                    "attr_id": 421,
	                    "value": 6.188
	                },
	                {
	                    "attr_id": 429,
	                    "value": 0
	                },
	                {
	                    "attr_id": 430,
	                    "value": 4.004
	                },
	                {
	                    "attr_id": 431,
	                    "value": 0
	                },
	                {
	                    "attr_id": 432,
	                    "value": 5.46
	                },
	                {
	                    "attr_id": 435,
	                    "value": 5.46
	                },
	                {
	                    "attr_id": 454,
	                    "value": 0.182
	                },
	                {
	                    "attr_id": 501,
	                    "value": 0.0018
	                },
	                {
	                    "attr_id": 502,
	                    "value": 0.0109
	                },
	                {
	                    "attr_id": 503,
	                    "value": 0.0109
	                },
	                {
	                    "attr_id": 504,
	                    "value": 0.0237
	                },
	                {
	                    "attr_id": 505,
	                    "value": 0.0218
	                },
	                {
	                    "attr_id": 506,
	                    "value": 0.0018
	                },
	                {
	                    "attr_id": 507,
	                    "value": 0.0018
	                },
	                {
	                    "attr_id": 508,
	                    "value": 0.0109
	                },
	                {
	                    "attr_id": 509,
	                    "value": 0.0018
	                },
	                {
	                    "attr_id": 510,
	                    "value": 0.0218
	                },
	                {
	                    "attr_id": 511,
	                    "value": 0.0109
	                },
	                {
	                    "attr_id": 512,
	                    "value": 0.0091
	                },
	                {
	                    "attr_id": 513,
	                    "value": 0.02
	                },
	                {
	                    "attr_id": 514,
	                    "value": 0.1274
	                },
	                {
	                    "attr_id": 515,
	                    "value": 0.0455
	                },
	                {
	                    "attr_id": 516,
	                    "value": 0.0164
	                },
	                {
	                    "attr_id": 517,
	                    "value": 0.0109
	                },
	                {
	                    "attr_id": 518,
	                    "value": 0.0182
	                },
	                {
	                    "attr_id": 601,
	                    "value": 0
	                },
	                {
	                    "attr_id": 605,
	                    "value": 0
	                },
	                {
	                    "attr_id": 606,
	                    "value": 0.051
	                },
	                {
	                    "attr_id": 607,
	                    "value": 0
	                },
	                {
	                    "attr_id": 608,
	                    "value": 0
	                },
	                {
	                    "attr_id": 609,
	                    "value": 0
	                },
	                {
	                    "attr_id": 610,
	                    "value": 0
	                },
	                {
	                    "attr_id": 611,
	                    "value": 0
	                },
	                {
	                    "attr_id": 612,
	                    "value": 0.0018
	                },
	                {
	                    "attr_id": 613,
	                    "value": 0.0437
	                },
	                {
	                    "attr_id": 614,
	                    "value": 0.0055
	                },
	                {
	                    "attr_id": 617,
	                    "value": 0.0127
	                },
	                {
	                    "attr_id": 618,
	                    "value": 0.0783
	                },
	                {
	                    "attr_id": 619,
	                    "value": 0.0164
	                },
	                {
	                    "attr_id": 620,
	                    "value": 0
	                },
	                {
	                    "attr_id": 621,
	                    "value": 0
	                },
	                {
	                    "attr_id": 626,
	                    "value": 0
	                },
	                {
	                    "attr_id": 627,
	                    "value": 0
	                },
	                {
	                    "attr_id": 628,
	                    "value": 0
	                },
	                {
	                    "attr_id": 629,
	                    "value": 0
	                },
	                {
	                    "attr_id": 630,
	                    "value": 0
	                },
	                {
	                    "attr_id": 631,
	                    "value": 0
	                },
	                {
	                    "attr_id": 636,
	                    "value": 21.84
	                },
	                {
	                    "attr_id": 645,
	                    "value": 0.0127
	                },
	                {
	                    "attr_id": 646,
	                    "value": 0.0928
	                }
	            ],
	            "nix_brand_name": null,
	            "nix_brand_id": null,
	            "nix_item_name": null,
	            "nix_item_id": null,
	            "upc": null,
	            "consumed_at": "2020-04-27T21:50:18+00:00",
	            "metadata": {
	                "is_raw_food": false
	            },
	            "source": 1,
	            "ndb_no": 9003,
	            "tags": {
	                "item": "apple",
	                "measure": null,
	                "quantity": "1.0",
	                "food_group": 3,
	                "tag_id": 384
	            },
	            "alt_measures": [
	                {
	                    "serving_weight": 242,
	                    "measure": "NLEA serving",
	                    "seq": 7,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 101,
	                    "measure": "extra small (2-1/2\" dia)",
	                    "seq": 6,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 149,
	                    "measure": "small (2-3/4\" dia)",
	                    "seq": 5,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 182,
	                    "measure": "medium (3\" dia)",
	                    "seq": 4,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 223,
	                    "measure": "large (3-1/4\" dia)",
	                    "seq": 3,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 109,
	                    "measure": "cup slices",
	                    "seq": 2,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 125,
	                    "measure": "cup, quartered or chopped",
	                    "seq": 1,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 28.35,
	                    "measure": "oz",
	                    "seq": 80,
	                    "qty": 1
	                },
	                {
	                    "serving_weight": 100,
	                    "measure": "g",
	                    "seq": null,
	                    "qty": 100
	                }
	            ],
	            "lat": null,
	            "lng": null,
	            "meal_type": 5,
	            "photo": {
	                "thumb": "https://d2xdmhkmkbyw75.cloudfront.net/384_thumb.jpg",
	                "highres": "https://d2xdmhkmkbyw75.cloudfront.net/384_highres.jpg",
	                "is_user_uploaded": false
	            },
	            "sub_recipe": null
	        }
	    ]
	}*/

}
