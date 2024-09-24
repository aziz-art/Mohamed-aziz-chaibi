import Recipe from '../models/recipi.js';

//Create
export async function createRecipe(req, res) {
  try {
    const { title, ingredients, instructions, category , image } = req.body;
    
    const recipe = new Recipe({ title, ingredients, instructions, category, image });
    const savedRecipe = await recipe.save();
    console.log('Recipe created:', savedRecipe);
    res.status(201).json(savedRecipe);
  } catch (error) {
    console.error('Error creating recipe:', error.message);
    res.status(500).json({ message: error.message });
  }
}


  
  // Example usage:
  /*createRecipe({
    title: 'Spaghetti Carbonara',
    ingredients: ['Spaghetti', 'Eggs', 'Parmesan', 'Pancetta'],
    instructions: 'Cook spaghetti. Mix eggs and Parmesan. Fry pancetta. Combine all ingredients.',
    //category: '60c72b2f9b1e8c1b8cfa1243', // Replace with a valid category ID
    image: 'img1.jpg',
  });*/

//ReadAll
export async function getAllRecipes(req, res) {
  try {
    const recipes = await Recipe.find().populate('category');
    console.log('All recipes:', recipes);
    res.status(200).json(recipes);
  } catch (error) {
    console.error('Error fetching recipes:', error.message);
    res.status(500).json({ message: error.message });
  }
}



  
  // Example usage:
 // getAllRecipes();

//Read
export async function getRecipeById(req, res) {
  const { id } = req.params;
  try {
    const recipe = await Recipe.findById(id);//.populate('category');
    if (!recipe) {
      console.log('Recipe not found');
      return;
    }
    res.status(200).json(recipe);
    console.log('Recipe found by id :', recipe);
  } catch (error) {
    console.error('Error fetching recipe:', error.message);
  }
}

// Example usage:
//getRecipeById('60c72b2f9b1e8c1b8cfa1243'); // Replace with a valid recipe ID


//update
export async function updateRecipe(req, res)  {
  const { id } = req.params;
  const data = req.body;

  try {
    // Create a sanitized copy of the data object
    const sanitizedData = JSON.parse(JSON.stringify(data));

    // Update the recipe with the sanitized data
    const updatedRecipe = await Recipe.findByIdAndUpdate(id, sanitizedData, {
      new: true,
      runValidators: true,
    }).populate('category');

    if (!updatedRecipe) {
      return res.status(404).json({ message: 'Recipe not found' });
    }

    res.status(200).json(updatedRecipe);
  } catch (error) {
    console.error('Error updating recipe:', error.message);
    res.status(500).json({ message: 'Error updating recipe', error: error.message });
  }
}



// Example usage:
/*updateRecipe('60c72b2f9b1e8c1b8cfa1243', {
  title: 'Updated Spaghetti Carbonara',
  ingredients: ['Spaghetti', 'Eggs', 'Parmesan', 'Bacon'],
  instructions: 'New instructions.',
});*/


//Delete
export async function deleteRecipe(req, res) {
  const { id } = req.params; // Extract the id parameter from the request URL

  try {
    const deletedRecipe = await Recipe.findByIdAndDelete(id);
    if (!deletedRecipe) {
      console.log('Recipe not found');
      res.status(404).send('Recipe not found');
      return;
    }
    console.log('Recipe deleted:', deletedRecipe);
    res.status(200).send(deletedRecipe);
  } catch (error) {
    console.error('Error deleting recipe:', error.message);
    res.status(500).send('Error deleting recipe');
  }
}


// Example usage:
//deleteRecipe('60c72b2f9b1e8c1b8cfa1243'); // Replace with a valid recipe ID
  