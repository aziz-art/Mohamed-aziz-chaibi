import User from '../models/user.js';

export async function add(req, res) {
    try {
        const user = await User.create(req.body);
        return res.status(200).json(user);
    } catch (error) {
        return res.status(500).json(error);
    }
}

export async function deleteUser(req, res) {
    try {
        const user = await User.findByIdAndDelete(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }
        return res.status(200).json({ message: 'User deleted' });
    } catch (err) {
        return res.status(500).json(err);
    }
}

export async function updateAccount(req, res) {
    try {
        const userId = req.user._id;
        const updates = req.body;

        const user = await User.findByIdAndUpdate(userId, updates, { new: true, runValidators: true });

        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }

        return res.status(200).json({ message: 'Account updated', user });
    } catch (err) {
        console.error('Error updating account:', err); // Log the error
        return res.status(500).json({ message: 'Internal server error', error: err.message });
    }
}

// Controller to get user profile (example)
export const getProfile = async (req, res) => {
    try {
        const user = await User.findById(req.userId);
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }

        res.status(200).json({ message: 'Profile found', user });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};
