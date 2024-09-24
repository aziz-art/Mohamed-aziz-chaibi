import {Router} from 'express';
import { createRecipe, getAllRecipes ,getRecipeById ,updateRecipe ,deleteRecipe } from '../controllers/recipiController.js';

let recipeRouter = Router();

recipeRouter.post('/create', createRecipe);
recipeRouter.delete('/delete/:id', deleteRecipe);
recipeRouter.put('/update/:id', updateRecipe);
recipeRouter.get('/getAll',getAllRecipes);
recipeRouter.get('/get/:id',getRecipeById);

export default recipeRouter;
