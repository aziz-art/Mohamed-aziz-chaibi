import mongoose from "mongoose";
import Category from "../models/category.js";

const recipeSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true
  },
  ingredients: {
    type: [String],
    required: true
  },
  instructions: {
    type: String,
    required: true
  },
  category: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Category',
    required: true ,
  },
  image: {
    type: String,
    required: true
  },
  createdAt: {
    type: Date,
    default: Date.now
    //elle  enregistre la date de création de la recette automatiquement njmou tne7iwha jme3aa 
  },
});


export default mongoose.model("Recipe", recipeSchema);