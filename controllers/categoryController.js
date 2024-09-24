// controllers/categoryController.js
import Category from '../models/category.js';

// Récupérer toutes les catégories
export function getAllCategory(req, res){
    try {
        const categories =  Category.find();
        res.json(categories);
    } catch (err) {
        res.status(500).json({ message: 'Erreur lors de la récupération des catégories' });
    }
};

// Ajouter une nouvelle catégorie
export async function addCategory(req, res){
    const { name , image} = req.body;

    try {
        const newCategory = new Category({ name , image});
        const savecategory = await newCategory.save();
        console.log('category created :', savecategory);
        res.status(201).json(savecategory);
    } catch (err) {
        res.status(500).json({ message: 'Erreur lors de l\'ajout de la catégorie' });
    }
};

// Supprimer une catégorie
export function deleteCategory (req, res) {
    const { id } = req.params;

    try {
        const deletedCategory =  Category.findByIdAndDelete(id);
        if (!deletedCategory) {
            return res.status(404).json({ message: 'Catégorie non trouvée' });
        }
        res.json({ message: 'Catégorie supprimée avec succès' });
    } catch (err) {
        res.status(500).json({ message: 'Erreur lors de la suppression de la catégorie' });
    }
};

// Mettre à jour une catégorie
export function updateCategory (req, res){
    const { id } = req.params;
    const { name } = req.body;

    try {
        const updatedCategory = Category.findByIdAndUpdate(id, { name }, { new: true });
        if (!updatedCategory) {
            return res.status(404).json({ message: 'Catégorie non trouvée' });
        }
        res.json(updatedCategory);
    } catch (err) {
        res.status(500).json({ message: 'Erreur lors de la mise à jour de la catégorie' });
    }
};

// Rechercher des catégories par nom
export function searchCategory (req, res) {
    const { keyword } = req.query;

    try {
        const categories = Category.find({ name: { $regex: keyword, $options: 'i' } });
        res.json(categories);
    } catch (err) {
        res.status(500).json({ message: 'Erreur lors de la recherche de catégories' });
    }
};