package com.example.recipi

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    val img: String,
    val code: String,
    val tittle: String,
    val des: String,
    val ing: List<Ingredient>,
    val category: String,
    val duration: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Ingredient.CREATOR) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(code)
        parcel.writeString(tittle)
        parcel.writeString(des)
        parcel.writeTypedList(ing)
        parcel.writeString(category)
        parcel.writeString(duration)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }

        fun generateDummyRecipes(): List<Recipe> {
            val recipes = mutableListOf<Recipe>()

            // Salads
            recipes.add(
                Recipe(
                    "caesar", "salad1", "Caesar Salad", "A classic Caesar salad with crisp romaine, creamy dressing, and crunchy croutons.",
                    listOf(
                        Ingredient("Lettuce", "200g", "lettuce"),
                        Ingredient("Dressing", "100mL", "dressing"),
                        Ingredient("Croutons", "50g", "croutons"),
                        Ingredient("Cheese", "30g", "cheese")
                    ),
                    "Salad", "15 mins"
                )
            )
            recipes.add(
                Recipe(
                    "greek", "salad2", "Greek Salad", "A refreshing Greek salad with tomatoes, cucumbers, olives, and feta cheese.",
                    listOf(
                        Ingredient("Tomatoes", "200g", "tomatoes"),
                        Ingredient("Cucumber", "150g", "cucumber"),
                        Ingredient("Cheese", "100g", "cheese"),
                        Ingredient("Olives", "50g", "olives"),
                        Ingredient("Onion", "50g", "onion"),
                        Ingredient("Oil", "2 tbsp", "oil")
                    ),
                    "Salad", "15 mins"
                )
            )
            recipes.add(
                Recipe(
                    "waldorf", "salad3", "Waldorf Salad", "A crisp salad with apples, walnuts, and celery dressed in mayonnaise.",
                    listOf(
                        Ingredient("Apples", "2, diced", "apples"),
                        Ingredient("Walnuts", "50g", "walnuts"),
                        Ingredient("Celery", "50g, chopped", "celery"),
                        Ingredient("Mayonnaise", "100mL", "mayonnaise")
                    ),
                    "Salad", "20 mins"
                )
            )
            recipes.add(
                Recipe(
                    "cobb", "salad4", "Cobb Salad", "A hearty salad with chicken, bacon, eggs, and avocado.",
                    listOf(
                        Ingredient("Greens", "200g", "greens"),
                        Ingredient("Chicken", "150g, cooked and diced", "chicken"),
                        Ingredient("Bacon", "50g, cooked and crumbled", "bacon"),
                        Ingredient("Eggs", "2, sliced", "eggs"),
                        Ingredient("Avocado", "1, sliced", "avocado"),
                        Ingredient("Dressing", "100mL", "dressing")
                    ),
                    "Salad", "25 mins"
                )
            )
            recipes.add(
                Recipe(
                    "caprese", "salad5", "Caprese Salad", "A simple Italian salad with fresh tomatoes, mozzarella, and basil.",
                    listOf(
                        Ingredient("Tomatoes", "200g, sliced", "tomatoes"),
                        Ingredient("Cheese", "100g, sliced", "cheese"),
                        Ingredient("Basil", "10g", "basil"),
                        Ingredient("Oil", "2 tbsp", "oil"),
                        Ingredient("Vinegar", "1 tbsp", "vinegar")
                    ),
                    "Salad", "10 mins"
                )
            )
            recipes.add(
                Recipe(
                    "spinach", "salad6", "Spinach Salad with Strawberries", "A light salad with spinach, strawberries, and almonds.",
                    listOf(
                        Ingredient("Spinach", "150g", "spinach"),
                        Ingredient("Strawberries", "100g, sliced", "strawberries"),
                        Ingredient("Almonds", "50g, toasted", "almonds"),
                        Ingredient("Dressing", "100mL", "dressing")
                    ),
                    "Salad", "15 mins"
                )
            )
            recipes.add(
                Recipe(
                    "quinoa", "salad7", "Quinoa Salad", "A protein-packed salad with quinoa, cucumbers, and cherry tomatoes.",
                    listOf(
                        Ingredient("Quinoa", "200g, cooked", "quinoa"),
                        Ingredient("Cucumber", "100g, diced", "cucumber"),
                        Ingredient("Tomatoes", "100g, halved", "tomatoes"),
                        Ingredient("Cheese", "50g, crumbled", "cheese"),
                        Ingredient("Lemon juice", "2 tbsp", "lemon")
                    ),
                    "Salad", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "nicoise", "salad8", "Nicoise Salad", "A French salad with tuna, green beans, and potatoes.",
                    listOf(
                        Ingredient("Tuna", "100g, canned", "tuna"),
                        Ingredient("Beans", "100g, blanched", "beans"),
                        Ingredient("Potatoes", "200g, boiled and sliced", "potato"),
                        Ingredient("Tomatoes", "100g", "tomatoes"),
                        Ingredient("Eggs", "2, quartered", "eggs"),
                        Ingredient("Olives", "50g", "olives")
                    ),
                    "Salad", "35 mins"
                )
            )

            // Main Dishes
            recipes.add(
                Recipe(
                    "spaghetti", "main1", "Spaghetti Bolognese", "A traditional Italian pasta dish with a rich beef and tomato sauce.",
                    listOf(
                        Ingredient("Spaghetti", "200g", "spaghetti"),
                        Ingredient("Beef", "300g", "meat"),
                        Ingredient("Sauce", "400mL", "sauce"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Oil", "2 tbsp", "oil"),
                        Ingredient("Basil", "1 tsp", "basil")
                    ),
                    "Main Dish", "45 mins"
                )
            )
            recipes.add(
                Recipe(
                    "curry", "main2", "Chicken Curry", "A flavorful curry with tender chicken pieces and aromatic spices.",
                    listOf(
                        Ingredient("Chicken", "300g, diced", "chicken"),
                        Ingredient("Curry", "2 tbsp", "curry"),
                        Ingredient("Milk", "400mL", "milk"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "3 cloves, minced", "garlic"),
                        Ingredient("Ginger", "1 inch, minced", "ginger"),
                        Ingredient("Rice", "200g", "rice")
                    ),
                    "Main Dish", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "steak", "main3", "Grilled Steak", "Juicy steak grilled to perfection with garlic butter.",
                    listOf(
                        Ingredient("Steak", "300g", "meat"),
                        Ingredient("Garlic", "3 cloves, minced", "garlic"),
                        Ingredient("Butter", "50g", "butter"),
                        Ingredient("Salt", "to taste", "salt"),
                        Ingredient("Pepper", "to taste", "pepper")
                    ),
                    "Main Dish", "20 mins"
                )
            )
            recipes.add(
                Recipe(
                    "risotto", "main4", "Mushroom Risotto", "Creamy risotto made with mushrooms and Parmesan cheese.",
                    listOf(
                        Ingredient("Rice", "200g", "rice"),
                        Ingredient("Mushrooms", "150g, sliced", "mushrooms"),
                        Ingredient("Cheese", "50g, grated", "cheese"),
                        Ingredient("Broth", "500mL", "broth"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Oil", "2 tbsp", "oil")
                    ),
                    "Main Dish", "40 mins"
                )
            )
            recipes.add(
                Recipe(
                    "fish", "main5", "Baked Salmon", "Tender salmon fillets baked with lemon and herbs.",
                    listOf(
                        Ingredient("Salmon", "2 fillets", "salmon"),
                        Ingredient("Lemon", "1, sliced", "lemon"),
                        Ingredient("Herbs", "1 tbsp, mixed", "herbs"),
                        Ingredient("Salt", "to taste", "salt"),
                        Ingredient("Pepper", "to taste", "pepper"),
                        Ingredient("Oil", "1 tbsp", "oil")
                    ),
                    "Main Dish", "25 mins"
                )
            )
            recipes.add(
                Recipe(
                    "tacos", "main6", "Beef Tacos", "Crispy tacos filled with seasoned beef and fresh toppings.",
                    listOf(
                        Ingredient("Beef", "300g, minced", "meat"),
                        Ingredient("Taco shells", "6", "taco"),
                        Ingredient("Lettuce", "100g, shredded", "lettuce"),
                        Ingredient("Cheese", "50g, grated", "cheese"),
                        Ingredient("Tomatoes", "2, diced", "tomatoes"),
                        Ingredient("Sauce", "50mL", "sauce"),
                        Ingredient("Onion", "1, chopped", "onion")
                    ),
                    "Main Dish", "20 mins"
                )
            )
            recipes.add(
                Recipe(
                    "pizza", "main7", "Margherita Pizza", "Classic pizza with fresh tomatoes, mozzarella, and basil.",
                    listOf(
                        Ingredient("Dough", "1 base", "dough"),
                        Ingredient("Sauce", "100mL", "sauce"),
                        Ingredient("Tomatoes", "100g, sliced", "tomatoes"),
                        Ingredient("Cheese", "100g, sliced", "cheese"),
                        Ingredient("Basil", "10g", "basil")
                    ),
                    "Main Dish", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "burg", "main8", "Classic Burger", "Juicy beef burger with lettuce, tomato, and cheese.",
                    listOf(
                        Ingredient("Beef", "150g, patty", "meat"),
                        Ingredient("Bun", "1", "bun"),
                        Ingredient("Lettuce", "30g", "lettuce"),
                        Ingredient("Tomato", "1 slice", "tomatoes"),
                        Ingredient("Cheese", "1 slice", "cheese"),
                        Ingredient("Sauce", "2 tbsp", "sauce"),
                        Ingredient("Onion", "1 slice", "onion")
                    ),
                    "Main Dish", "15 mins"
                )
            )
            // Desserts
            recipes.add(
                Recipe(
                    "cheesecake", "dessert1", "Classic Cheesecake", "A creamy cheesecake with a buttery graham cracker crust.",
                    listOf(
                        Ingredient("Cream cheese", "500g", "cheese"),
                        Ingredient("Sugar", "200g", "sugar"),
                        Ingredient("Eggs", "3", "eggs"),
                        Ingredient("Vanilla extract", "2 tsp", "vanilla"),
                        Ingredient("Graham cracker crumbs", "200g", "crackers"),
                        Ingredient("Butter", "100g", "butter")
                    ),
                    "Dessert", "1 hr 15 mins"
                )
            )
            recipes.add(
                Recipe(
                    "brownies", "dessert2", "Chocolate Brownies", "Fudgy brownies made with rich chocolate and walnuts.",
                    listOf(
                        Ingredient("Butter", "115g", "butter"),
                        Ingredient("Sugar", "200g", "sugar"),
                        Ingredient("Cocoa powder", "50g", "cocoa"),
                        Ingredient("Eggs", "2", "eggs"),
                        Ingredient("Vanilla extract", "1 tsp", "vanilla"),
                        Ingredient("Flour", "65g", "flour"),
                        Ingredient("Walnuts", "50g", "walnuts")
                    ),
                    "Dessert", "45 mins"
                )
            )
            recipes.add(
                Recipe(
                    "tiramisu", "dessert3", "Tiramisu", "A classic Italian dessert with layers of coffee-soaked ladyfingers and mascarpone cheese.",
                    listOf(
                        Ingredient("Mascarpone cheese", "250g", "cheese"),
                        Ingredient("Sugar", "100g", "sugar"),
                        Ingredient("Egg yolks", "4", "eggs"),
                        Ingredient("Espresso coffee", "200mL", "coffee"),
                        Ingredient("Ladyfingers", "200g", "cookies"),
                        Ingredient("Cocoa powder", "2 tbsp", "cocoa")
                    ),
                    "Dessert", "4 hrs"
                )
            )
            recipes.add(
                Recipe(
                    "pannacotta", "dessert4", "Panna Cotta", "A creamy Italian dessert made with vanilla and gelatin.",
                    listOf(
                        Ingredient("Heavy cream", "500mL", "cream"),
                        Ingredient("Sugar", "100g", "sugar"),
                        Ingredient("Vanilla extract", "2 tsp", "vanilla"),
                        Ingredient("Gelatin", "1 tbsp", "gelatin"),
                        Ingredient("Milk", "100mL", "milk")
                    ),
                    "Dessert", "4 hrs"
                )
            )
            recipes.add(
                Recipe(
                    "macarons", "dessert5", "French Macarons", "Delicate almond meringue cookies with a creamy filling.",
                    listOf(
                        Ingredient("Almond flour", "125g", "flour"),
                        Ingredient("Powdered sugar", "125g", "sugar"),
                        Ingredient("Egg whites", "90g", "eggs"),
                        Ingredient("Granulated sugar", "25g", "sugar"),
                        Ingredient("Butter", "100g", "butter"),
                        Ingredient("Vanilla extract", "1 tsp", "vanilla")
                    ),
                    "Dessert", "2 hrs"
                )
            )
            recipes.add(
                Recipe(
                    "burnt", "dessert6", "Crème Brûlée", "A rich custard topped with caramelized sugar.",
                    listOf(
                        Ingredient("Heavy cream", "500mL", "cream"),
                        Ingredient("Egg yolks", "6", "eggs"),
                        Ingredient("Sugar", "100g", "sugar"),
                        Ingredient("Vanilla bean", "1", "vanilla"),
                        Ingredient("Brown sugar", "50g", "sugar")
                    ),
                    "Dessert", "2 hrs"
                )
            )
            recipes.add(
                Recipe(
                    "applepie", "dessert7", "Apple Pie", "A classic apple pie with a buttery crust and spiced apples.",
                    listOf(
                        Ingredient("Apples", "6, peeled and sliced", "apples"),
                        Ingredient("Sugar", "150g", "sugar"),
                        Ingredient("Cinnamon", "1 tsp", "cinnamon"),
                        Ingredient("Butter", "50g", "butter"),
                        Ingredient("Pie crust", "2 sheets", "crust")
                    ),
                    "Dessert", "1 hr 30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "lemonbars", "dessert8", "Lemon Bars", "Tangy lemon bars with a buttery shortbread crust.",
                    listOf(
                        Ingredient("Butter", "115g", "butter"),
                        Ingredient("Sugar", "200g", "sugar"),
                        Ingredient("Flour", "190g", "flour"),
                        Ingredient("Lemon juice", "100mL", "lemon"),
                        Ingredient("Eggs", "3", "eggs"),
                        Ingredient("Powdered sugar", "50g", "sugar")
                    ),
                    "Dessert", "1 hr"
                )
            )

// Drinks
            recipes.add(
                Recipe(
                    "mojito", "drink1", "Mojito", "A refreshing Cuban cocktail with lime, mint, and rum.",
                    listOf(
                        Ingredient("White rum", "50mL", "rum"),
                        Ingredient("Sugar", "2 tbsp", "sugar"),
                        Ingredient("Lime juice", "30mL", "lime"),
                        Ingredient("Mint leaves", "10", "mint"),
                        Ingredient("Soda water", "150mL", "soda"),
                        Ingredient("Ice cubes", "as needed", "ice")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "margarita", "drink2", "Margarita", "A classic Mexican cocktail with tequila, lime, and triple sec.",
                    listOf(
                        Ingredient("Tequila", "50mL", "tequila"),
                        Ingredient("Lime juice", "30mL", "lime"),
                        Ingredient("Triple sec", "20mL", "triple_sec"),
                        Ingredient("Salt", "for rimming glass", "salt"),
                        Ingredient("Ice cubes", "as needed", "ice")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "colada", "drink3", "Piña Colada", "A tropical cocktail with pineapple, coconut, and rum.",
                    listOf(
                        Ingredient("White rum", "50mL", "rum"),
                        Ingredient("Pineapple juice", "100mL", "pineapple"),
                        Ingredient("Coconut cream", "50mL", "coconut"),
                        Ingredient("Ice cubes", "as needed", "ice")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "tea", "drink4", "Iced Tea", "A refreshing cold tea flavored with lemon and mint.",
                    listOf(
                        Ingredient("Black tea bags", "4", "tea"),
                        Ingredient("Sugar", "100g", "sugar"),
                        Ingredient("Lemon slices", "4", "lemon"),
                        Ingredient("Mint leaves", "10", "mint"),
                        Ingredient("Water", "1L", "water")
                    ),
                    "Drink", "10 mins"
                )
            )
            recipes.add(
                Recipe(
                    "smoothie", "drink5", "Berry Smoothie", "A healthy smoothie with mixed berries, yogurt, and honey.",
                    listOf(
                        Ingredient("Mixed berries", "200g", "berries"),
                        Ingredient("Yogurt", "150g", "yogurt"),
                        Ingredient("Honey", "2 tbsp", "honey"),
                        Ingredient("Milk", "100mL", "milk")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "milkshake", "drink6", "Chocolate Milkshake", "A creamy milkshake with rich chocolate flavor.",
                    listOf(
                        Ingredient("Milk", "200mL", "milk"),
                        Ingredient("Chocolate ice cream", "2 scoops", "icecream"),
                        Ingredient("Chocolate syrup", "2 tbsp", "syrup"),
                        Ingredient("Whipped cream", "for topping", "cream")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "latte", "drink7", "Latte", "A smooth espresso drink with steamed milk and foam.",
                    listOf(
                        Ingredient("Espresso", "30mL", "coffee"),
                        Ingredient("Milk", "200mL", "milk"),
                        Ingredient("Sugar", "to taste", "sugar")
                    ),
                    "Drink", "5 mins"
                )
            )
            recipes.add(
                Recipe(
                    "cappuccino", "drink8", "Cappuccino", "A classic Italian coffee with espresso, steamed milk, and foam.",
                    listOf(
                        Ingredient("Espresso", "30mL", "coffee"),
                        Ingredient("Milk", "150mL", "milk"),
                        Ingredient("Foam", "100mL", "foam")
                    ),
                    "Drink", "5 mins"
                )
            )

// Soups
            recipes.add(
                Recipe(
                    "chickennoodle", "soup1", "Chicken Noodle Soup", "Classic chicken noodle soup with vegetables and noodles.",
                    listOf(
                        Ingredient("Chicken", "200g", "chicken"),
                        Ingredient("Noodles", "100g", "noodles"),
                        Ingredient("Carrots", "2, sliced", "carrots"),
                        Ingredient("Celery", "2 stalks, sliced", "celery"),
                        Ingredient("Chicken broth", "1L", "broth"),
                        Ingredient("Onion", "1, chopped", "onion")
                    ),
                    "Soup", "45 mins"
                )
            )
            recipes.add(
                Recipe(
                    "tomato", "soup2", "Tomato Soup", "Creamy tomato soup with basil and a hint of garlic.",
                    listOf(
                        Ingredient("Tomatoes", "800g", "tomatoes"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Vegetable broth", "750mL", "broth"),
                        Ingredient("Basil", "10 leaves", "basil"),
                        Ingredient("Cream", "100mL", "cream")
                    ),
                    "Soup", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "lentil", "soup3", "Lentil Soup", "Hearty lentil soup with vegetables and spices.",
                    listOf(
                        Ingredient("Lentils", "200g", "lentils"),
                        Ingredient("Carrots", "2, chopped", "carrots"),
                        Ingredient("Celery", "2 stalks, chopped", "celery"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Vegetable broth", "1L", "broth"),
                        Ingredient("Cumin", "1 tsp", "cumin")
                    ),
                    "Soup", "40 mins"
                )
            )
            recipes.add(
                Recipe(
                    "pumpkin", "soup4", "Pumpkin Soup", "Creamy pumpkin soup with a hint of nutmeg and cinnamon.",
                    listOf(
                        Ingredient("Pumpkin", "500g, cubed", "pumpkin"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Vegetable broth", "1L", "broth"),
                        Ingredient("Cream", "100mL", "cream"),
                        Ingredient("Nutmeg", "1 tsp", "nutmeg"),
                        Ingredient("Cinnamon", "1 tsp", "cinnamon")
                    ),
                    "Soup", "40 mins"
                )
            )
            recipes.add(
                Recipe(
                    "mushroomsoup", "soup5", "Mushroom Soup", "Rich mushroom soup with garlic and thyme.",
                    listOf(
                        Ingredient("Mushrooms", "500g, sliced", "mushrooms"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Thyme", "1 tsp", "thyme"),
                        Ingredient("Vegetable broth", "1L", "broth"),
                        Ingredient("Cream", "100mL", "cream")
                    ),
                    "Soup", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "frenchonion", "soup6", "French Onion Soup", "Classic French onion soup with caramelized onions and Gruyère cheese.",
                    listOf(
                        Ingredient("Onions", "4, sliced", "onion"),
                        Ingredient("Beef broth", "1L", "broth"),
                        Ingredient("Butter", "50g", "butter"),
                        Ingredient("Thyme", "1 tsp", "thyme"),
                        Ingredient("Bay leaf", "1", "bay_leaf"),
                        Ingredient("Gruyère cheese", "100g, grated", "cheese"),
                        Ingredient("Baguette", "sliced", "bread")
                    ),
                    "Soup", "1 hr"
                )
            )
            recipes.add(
                Recipe(
                    "peanutsoup", "soup7", "Peanut Soup", "Creamy peanut soup with a touch of spice.",
                    listOf(
                        Ingredient("Peanut butter", "200g", "peanut_butter"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Chicken broth", "1L", "broth"),
                        Ingredient("Tomatoes", "400g, diced", "tomatoes"),
                        Ingredient("Chili powder", "1 tsp", "chili")
                    ),
                    "Soup", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "broccoli", "soup8", "Broccoli Cheddar Soup", "Creamy broccoli soup with sharp cheddar cheese.",
                    listOf(
                        Ingredient("Broccoli", "500g, chopped", "broccoli"),
                        Ingredient("Onion", "1, chopped", "onion"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic"),
                        Ingredient("Chicken broth", "1L", "broth"),
                        Ingredient("Cheddar cheese", "200g, grated", "cheese"),
                        Ingredient("Cream", "100mL", "cream")
                    ),
                    "Soup", "30 mins"
                )
            )
            recipes.add(
                Recipe(
                    "chips", "snack1", "Potato Chips", "Crispy homemade potato chips seasoned with salt.",
                    listOf(
                        Ingredient("Potatoes", "4, thinly sliced", "potatoes"),
                        Ingredient("Salt", "To taste", "salt"),
                        Ingredient("Oil", "For frying", "oil")
                    ),
                    "Snacks", "30 mins"
                )
            )

            recipes.add(
                Recipe(
                    "popcorn", "snack2", "Popcorn", "Light and fluffy popcorn with a touch of butter.",
                    listOf(
                        Ingredient("Popcorn Kernels", "100g", "popcorn"),
                        Ingredient("Butter", "50g, melted", "butter"),
                        Ingredient("Salt", "To taste", "salt")
                    ),
                    "Snacks", "15 mins"
                )
            )

            recipes.add(
                Recipe(
                    "nachos", "snack3", "Nachos", "Crunchy nachos topped with cheese and jalapenos.",
                    listOf(
                        Ingredient("Tortilla Chips", "200g", "tortilla_chips"),
                        Ingredient("Cheddar Cheese", "100g, shredded", "cheddar_cheese"),
                        Ingredient("Jalapenos", "To taste, sliced", "jalapenos"),
                        Ingredient("Sour Cream", "50mL", "sour_cream")
                    ),
                    "Snacks", "20 mins"
                )
            )

            recipes.add(
                Recipe(
                    "samosas", "snack4", "Samosas", "Crispy pastries filled with spiced potatoes and peas.",
                    listOf(
                        Ingredient("Samosa Pastry", "10 sheets", "samosa_pastry"),
                        Ingredient("Potatoes", "3, boiled and mashed", "potatoes"),
                        Ingredient("Green Peas", "100g", "green_peas"),
                        Ingredient("Spices", "To taste", "spices")
                    ),
                    "Snacks", "1 hr"
                )
            )

            recipes.add(
                Recipe(
                    "springrolls", "snack5", "Spring Rolls", "Crispy spring rolls with a mix of vegetables.",
                    listOf(
                        Ingredient("Spring Roll Wrappers", "10 sheets", "spring_roll_wrappers"),
                        Ingredient("Cabbage", "100g, shredded", "cabbage"),
                        Ingredient("Carrots", "2, julienned", "carrots"),
                        Ingredient("Bean Sprouts", "100g", "bean_sprouts")
                    ),
                    "Snacks", "30 mins"
                )
            )

            recipes.add(
                Recipe(
                    "cheesesticks", "snack6", "Cheese Sticks", "Golden fried cheese sticks with marinara sauce.",
                    listOf(
                        Ingredient("Mozzarella Cheese", "200g, cut into sticks", "mozzarella_cheese"),
                        Ingredient("Breadcrumbs", "100g", "breadcrumbs"),
                        Ingredient("Egg", "1, beaten", "egg"),
                        Ingredient("Oil", "For frying", "oil")
                    ),
                    "Snacks", "20 mins"
                )
            )

            recipes.add(
                Recipe(
                    "hummus", "snack7", "Hummus", "Creamy hummus made from chickpeas and tahini.",
                    listOf(
                        Ingredient("Chickpeas", "200g, cooked", "chickpeas"),
                        Ingredient("Tahini", "50g", "tahini"),
                        Ingredient("Lemon Juice", "2 tbsp", "lemon"),
                        Ingredient("Garlic", "2 cloves, minced", "garlic")
                    ),
                    "Snacks", "15 mins"
                )
            )


            return recipes
        }
    }
}

data class Ingredient(
    val name: String,
    val quantity: String,
    val imgName: String // Add this property to store image name
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(quantity)
        parcel.writeString(imgName) // Write imgName to the parcel
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}
