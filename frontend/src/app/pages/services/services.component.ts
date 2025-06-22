import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent {
  services = [
    {
      icon: '📦',
      title: 'Transport de Colis Express',
      description: 'Envoyez vos colis de toute taille en toute sécurité. Notre réseau de conducteurs fiables garantit une livraison rapide et efficace.'
    },
    {
      icon: '🗺️',
      title: 'Suivi en Temps Réel',
      description: 'Gardez un œil sur votre envoi à chaque étape du trajet grâce à notre système de géolocalisation intégré et transparent.'
    },
    {
      icon: '🛡️',
      title: 'Assurance Intégrée',
      description: 'Chaque envoi est couvert par notre assurance partenaire, vous offrant une tranquillité d\'esprit totale.'
    },
    {
      icon: '💸',
      title: 'Tarifs Compétitifs',
      description: 'Profitez de tarifs avantageux grâce à notre modèle de co-transport qui optimise les trajets et réduit les coûts.'
    },
    {
      icon: '💬',
      title: 'Support Client 24/7',
      description: 'Notre équipe de support est disponible à tout moment pour répondre à vos questions et résoudre vos problèmes.'
    },
    {
      icon: '🌱',
      title: 'Solution Écologique',
      description: 'Contribuez à un avenir plus vert en utilisant des trajets existants et en réduisant les émissions de carbone.'
    }
  ];
}
