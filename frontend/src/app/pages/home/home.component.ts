import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  features = [
    {
      icon: '🚗',
      title: 'Conducteurs',
      description: 'Monétisez vos trajets en transportant des colis et optimisez vos déplacements.'
    },
    {
      icon: '📦',
      title: 'Expéditeurs',
      description: 'Trouvez des transporteurs fiables et réduisez vos coûts de livraison.'
    },
    {
      icon: '🔒',
      title: 'Sécurisé',
      description: 'Transactions sécurisées et suivi en temps réel de vos envois.'
    },
    {
      icon: '🌱',
      title: 'Écologique',
      description: 'Réduisez l\'empreinte carbone en optimisant les trajets existants.'
    }
  ];

  steps = [
    {
      number: 1,
      title: 'Inscrivez-vous',
      description: 'Créez votre compte en quelques secondes'
    },
    {
      number: 2,
      title: 'Publiez votre trajet',
      description: 'Définissez votre itinéraire et vos disponibilités'
    },
    {
      number: 3,
      title: 'Connectez-vous',
      description: 'Trouvez des partenaires et commencez à transporter'
    }
  ];
}
