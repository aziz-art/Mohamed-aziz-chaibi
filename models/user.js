import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const userSchema = new mongoose.Schema({
  email: { 
      type: String,
      required: true,
      unique: true 
    },
  password: { 
      type: String,
      required: true 
    },
  resetPasswordToken: { 
    type: String 
  },
  resetPasswordExpires: {
     type: Date 
    },
});

userSchema.methods.hashPassword = function (password) {
  return bcrypt.hashSync(password, bcrypt.genSaltSync(10));
};

userSchema.methods.comparePassword = function (password) {
  return bcrypt.compareSync(password, this.password);
};

export default mongoose.model("User", userSchema);

