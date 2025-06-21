import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  teamMembers = [
    {
      name: 'Alice Dubois',
      role: 'CEO & Fondatrice',
      imageUrl: 'https://i.pravatar.cc/150?img=1',
      description: 'Visionnaire passionnée par la logistique et la technologie, Alice a fondé DeliveryMatch pour révolutionner le co-transport.'
    },
    {
      name: 'Bruno Charpentier',
      role: 'CTO & Co-fondateur',
      imageUrl: 'https://i.pravatar.cc/150?img=2',
      description: 'Expert en développement logiciel, Bruno est l\'architecte de notre plateforme innovante et sécurisée.'

    },
    {
      name: 'Carla Moreno',
      role: 'Responsable des Opérations',
      imageUrl: 'https://i.pravatar.cc/150?img=3',
      description: 'Carla veille à ce que chaque livraison se déroule sans accroc, optimisant les processus pour une efficacité maximale.'
    }
  ];

  companyValues = [
    {
      icon: '💡',
      title: 'Innovation',
      description: 'Nous repoussons constamment les limites de la technologie pour offrir des solutions de transport plus intelligentes.'
    },
    {
      icon: '🤝',
      title: 'Confiance',
      description: 'La sécurité et la transparence sont au cœur de notre plateforme pour garantir des transactions fiables.'
    },
    {
      icon: '🌱',
      title: 'Durabilité',
      description: 'Nous nous engageons à réduire l\'empreinte carbone en optimisant les trajets et en promouvant une logistique verte.'
    },
    {
      icon: '❤️',
      title: 'Communauté',
      description: 'Nous bâtissons un réseau solidaire de conducteurs et d\'expéditeurs pour un bénéfice mutuel.'
    }
  ];
}
