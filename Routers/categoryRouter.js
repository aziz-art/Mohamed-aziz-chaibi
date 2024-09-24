// routes/categoryRoutes.js
import {Router} from 'express';
import {
    getAllCategory,
    addCategory,
    deleteCategory,
    updateCategory,
    searchCategory
} from '../controllers/categoryController.js';

let categoryRouter = Router();

categoryRouter.get('/getCategories', getAllCategory);
categoryRouter.post('/addCategory', addCategory);
categoryRouter.delete('/deleteCategories/:id', deleteCategory);
categoryRouter.put('/updateCategories/:id', updateCategory);
categoryRouter.get('/categories/search', searchCategory);

export default categoryRouter;