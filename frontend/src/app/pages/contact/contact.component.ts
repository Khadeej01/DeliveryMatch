import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  contactForm: FormGroup;
  isSubmitted = false;

  constructor(private fb: FormBuilder) {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      subject: ['', Validators.required],
      message: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.contactForm.valid) {
      console.log('Formulaire soumis :', this.contactForm.value);
      // Ici, vous pourriez appeler un service pour envoyer les données
      this.contactForm.reset();
      this.isSubmitted = false;
      alert('Merci pour votre message ! Nous vous répondrons bientôt.');
    } else {
      console.log('Formulaire invalide');
    }
  }

  // Getters pour un accès facile dans le template
  get name() { return this.contactForm.get('name'); }
  get email() { return this.contactForm.get('email'); }
  get subject() { return this.contactForm.get('subject'); }
  get message() { return this.contactForm.get('message'); }
}
