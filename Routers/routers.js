import express from 'express';
import { add, deleteUser, updateAccount, getProfile } from '../controllers/usercontrollers.js'; // Adjust the path as necessary
import { auth } from '../middlewares/auth.js'; // Assuming you have a middleware to verify tokens

const router = express.Router();

// Route to create a new user
router.post('/add', add);

// Route to delete a user by ID
router.delete('/delete/:id', deleteUser);

// Route to update a user's account (assuming the user is authenticated)
router.put('/update', auth, updateAccount);

// Route to get the profile of the authenticated user
router.get('/profile', auth, getProfile);

export default router;
